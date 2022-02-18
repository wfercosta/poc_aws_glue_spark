package br.com.example.ms.metrics.entrypoint.rest;

import lombok.Data;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
public class EventEntryView {

	private LocalDateTime occurrenceDate;
	private Duration duration;
	private boolean partial;
	private String explanation;

	private List<EventEntryEndpointView> endpoints = new ArrayList<>();
}
