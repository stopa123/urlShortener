package com.mycompany.urlshortener;
//com.mycompany.urlshortener.lambdaHandler:: handleRequest

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

public class lambdaHandler implements RequestStreamHandler {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(UrlShortener.class);

        } catch (ContainerInitializationException e) {
            throw new RuntimeException("Could not Initialize Application \n\n" + e.getMessage());
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        try {
            // Set initialization timeout before processing the stream
            LambdaContainerHandler.getContainerConfig().setInitializationTimeout(30000);

            // Add error handling and logging
            if (inputStream == null || outputStream == null) {
                throw new IllegalArgumentException("Input or output stream cannot be null");
            }

            // Add context logging for debugging
            if (context != null) {
                context.getLogger().log("Processing request with remaining time: " + context.getRemainingTimeInMillis());
            }

            // Process the stream with proper resource management
            handler.proxyStream(inputStream, outputStream, context);

        } catch (Exception e) {
            context.getLogger().log("Error processing request: " + e.getMessage());
            throw new IOException("Failed to process request", e);
        } finally {
            // Ensure streams are properly closed
            try {
                inputStream.close();
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                context.getLogger().log("Error closing streams: " + e.getMessage());
            }
        }
    }

    /*
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
        LambdaContainerHandler.getContainerConfig().setInitializationTimeout(30000);
    }
     */
}
