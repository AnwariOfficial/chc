package com.afghancoders.service;

import java.util.Optional;

import com.afghancoders.domain.ConfirmationToken;

public interface ConfirmationTokenService {
	public void saveConfirmationToken(ConfirmationToken token);
	public Optional<ConfirmationToken> getToken(String token);
	public int setConfirmedAt(String token);

}
