package br.com.example.ms.metrics.entrypoint.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PagingResponseEnvelopView<T> {

	private final List<T> data;
	private final MetaView meta;

	public static <T> PagingResponseEnvelopView<T> of(final List<T> data, final int totalPages, final long totalRecords) {
		return new PagingResponseEnvelopView<T>(data, new MetaView(totalPages, totalRecords));
	}
}
