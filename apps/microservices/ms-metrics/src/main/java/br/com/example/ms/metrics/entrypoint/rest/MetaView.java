package br.com.example.ms.metrics.entrypoint.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MetaView {
	private final int totalPages;
	private final int totalRecords;
}
