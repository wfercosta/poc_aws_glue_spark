package br.com.example.ms.metrics.core.regulatory.outages;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OutageEventEntry {

	private LocalDateTime occurrenceDate;
	private Duration duration;
	private boolean partial;
	private String explanation;

	private List<Endpoint> endpoints = new ArrayList<>();

}
