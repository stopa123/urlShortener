package com.mycompany.urlshortener;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class lambdaHandler {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    public class LambdaHandler implements RequestStreamHandler {

        static {

            try {
                handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(UrlShortener.class);

            } catch (ContainerInitializationException e) {
                throw new RuntimeException("Could not Initialize Application \n\n" + e.getMessage());
            }
        }

        @Override
        public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
            handler.proxyStream(inputStream, outputStream, context);
        }
    }

}
