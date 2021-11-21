package br.com.example.ms.metrics.core.regulatory.outages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PageResponse<T> {

	private final T content;
	private final int totalPages;
	private final long totalRecords;

	public static <T> PageResponse<T> of(final T content, final int totalPages, final long totalRecords) {
		return new PageResponse(content, totalPages, totalRecords);
	}
}
