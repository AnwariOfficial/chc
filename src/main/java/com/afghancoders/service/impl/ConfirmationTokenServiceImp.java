package com.afghancoders.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.afghancoders.domain.ConfirmationToken;
import com.afghancoders.repository.ConfirmationTokenRepository;
import com.afghancoders.service.ConfirmationTokenService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ConfirmationTokenServiceImp implements ConfirmationTokenService {
	private final ConfirmationTokenRepository repository;

	@Override
	public void saveConfirmationToken(ConfirmationToken token) {
		repository.save(token);
		
	}
	public Optional<ConfirmationToken> getToken(String token) {
        return repository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return repository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

}
