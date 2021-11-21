package br.com.example.ms.metrics.core.regulatory.outages;

public interface UseCase<R, I> {

	R execute(I in);
}
