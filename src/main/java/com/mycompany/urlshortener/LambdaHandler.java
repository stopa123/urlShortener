package com.mycompany.urlshortener;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LambdaHandler implements RequestStreamHandler {
    private static final Logger logger = LoggerFactory.getLogger(LambdaHandler.class);
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(UrlShortener.class);
            // Set initialization timeout
            LambdaContainerHandler.getContainerConfig().setInitializationTimeout(30000);
        } catch (ContainerInitializationException e) {
            logger.error("Could not initialize Spring Boot application", e);
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        if (context != null) {
            logger.info("Handling request with remaining time: {} ms", context.getRemainingTimeInMillis());
        }

        try {
            if (inputStream == null || outputStream == null) {
                throw new IllegalArgumentException("Input or output stream cannot be null");
            }

            // Process the request through Spring Boot handler
            handler.proxyStream(inputStream, outputStream, context);

        } catch (Exception e) {
            logger.error("Error processing request", e);
            if (context != null) {
                context.getLogger().log("Error processing request: " + e.getMessage());
            }
            throw new IOException("Failed to process request", e);
        } 
        
       /*  finally {
            try {
                //Clean up resources
                inputStream.close();
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                logger.error("Error closing streams", e);
                if (context != null) {
                    context.getLogger().log("Error closing streams: " + e.getMessage());
                }
            }
        }*/
    }
}