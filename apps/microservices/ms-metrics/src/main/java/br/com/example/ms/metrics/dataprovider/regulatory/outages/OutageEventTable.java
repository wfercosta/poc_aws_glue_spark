package br.com.example.ms.metrics.dataprovider.regulatory.outages;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OutageEventTable {

	private LocalDateTime occurrenceDate;
	private Duration duration;
	private boolean partial;
	private String explanation;

	private List<EndpointTable> endpoints = new ArrayList<>();
}
