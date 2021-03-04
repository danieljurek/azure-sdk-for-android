// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.chat.implementation;

import com.azure.communication.common.CommunicationTokenCredential;
import com.azure.core.credential.AccessToken;
import com.azure.core.credential.TokenCredential;
import com.azure.core.credential.TokenRequestContext;

import java.util.concurrent.ExecutionException;

import reactor.core.publisher.Mono;

/**
 * This class serves as a CommunicationTokenCredential wrapper that
 * allows using BearerAuthenticationPolicy in different clients
 */
public class CommunicationBearerTokenCredential implements TokenCredential {
    private final CommunicationTokenCredential credential;

    /**
     * Creates a CommunicationTokenCredential
     *
     * @param communicationTokenCredential The {@link CommunicationTokenCredential} to use
     * in the BearerAuthenticationPolicy.
     */
    public CommunicationBearerTokenCredential(CommunicationTokenCredential communicationTokenCredential) {
        credential = communicationTokenCredential;
    }

    @Override
    public Mono<AccessToken> getToken(TokenRequestContext request) {
        try {
            return credential.getToken();
        } catch (InterruptedException e) {
            return Mono.error(e);
        } catch (ExecutionException e) {
            return Mono.error(e);
        }
    }
}
