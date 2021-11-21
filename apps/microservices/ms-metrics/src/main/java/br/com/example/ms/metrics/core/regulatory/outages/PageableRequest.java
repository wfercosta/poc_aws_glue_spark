package br.com.example.ms.metrics.core.regulatory.outages;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class PageableRequest {

	private final int page;
	private final int size;

	public static PageableRequest of(final int page, final int size) {
		return new PageableRequest(page, size);
	}
}
