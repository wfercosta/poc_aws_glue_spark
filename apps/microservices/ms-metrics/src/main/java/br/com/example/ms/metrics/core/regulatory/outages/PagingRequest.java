package br.com.example.ms.metrics.core.regulatory.outages;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class PagingRequest {

	private final int page;
	private final int size;

	public static PagingRequest of(final int page, final int size) {
		return new PagingRequest(page, size);
	}
}
