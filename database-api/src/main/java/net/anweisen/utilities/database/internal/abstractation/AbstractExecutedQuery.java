package net.anweisen.utilities.database.internal.abstractation;

import net.anweisen.utilities.database.ExecutedQuery;
import net.anweisen.utilities.database.Result;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public abstract class AbstractExecutedQuery implements ExecutedQuery {

	protected final List<Result> results;

	public AbstractExecutedQuery(@Nonnull List<Result> results) {
		this.results = results;
	}

	@Nonnull
	@Override
	public Optional<Result> first() {
		if (results.isEmpty()) return Optional.empty();
		return Optional.ofNullable(results.get(0));
	}

	@Nonnull
	@Override
	public Optional<Result> get(int index) {
		if (index >= results.size()) return Optional.empty();
		return Optional.ofNullable(results.get(index));
	}

	@Nonnull
	@Override
	public Stream<Result> all() {
		return results.stream();
	}

	@Nonnull
	@Override
	public <C extends Collection<? super Result>> C into(@Nonnull C collection) {
		collection.addAll(results);
		return collection;
	}

	@Override
	public void forEach(@Nonnull Consumer<? super Result> action) {
		results.forEach(action);
	}

	@Override
	public boolean isEmpty() {
		return results.isEmpty();
	}

	@Override
	public boolean isSet() {
		return !results.isEmpty();
	}

	@Override
	public int size() {
		return results.size();
	}

	@Override
	public void print() {
		if (results.isEmpty()) {
			System.out.println("<Empty Result>");
			return;
		}

		int index = 0;
		for (Result result : results) {
			System.out.print(index + ". | ");
			for (Entry<String, Object> entry : result.values().entrySet()) {
				System.out.print(entry.getKey() + " = '" + entry.getValue() + "' ");
			}
			System.out.println();
			index++;
		}
	}

}
