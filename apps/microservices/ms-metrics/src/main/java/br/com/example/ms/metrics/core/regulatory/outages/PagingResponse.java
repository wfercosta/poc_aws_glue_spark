package br.com.example.ms.metrics.core.regulatory.outages;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PagingResponse<T> {

	private final T data;
	private final int totalPages;
	private final int totalRecords;

	public static <T> PagingResponse<T> create(final T data, final int totalPages, final int totalRecords) {
		return new PagingResponse(data, totalPages, totalRecords);
	}
}
