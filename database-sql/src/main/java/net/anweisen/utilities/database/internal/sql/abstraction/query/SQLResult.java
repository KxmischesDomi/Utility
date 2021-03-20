package net.anweisen.utilities.database.internal.sql.abstraction.query;

import net.anweisen.utilities.commons.config.Document;
import net.anweisen.utilities.commons.config.document.AbstractDocument;
import net.anweisen.utilities.commons.config.document.EmptyDocument;
import net.anweisen.utilities.commons.config.document.GsonDocument;
import net.anweisen.utilities.commons.config.document.readonly.ReadOnlyGsonDocument;
import net.anweisen.utilities.database.Result;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 2.0
 */
public final class SQLResult extends AbstractDocument implements Result {

	private final Map<String, Object> values;

	public SQLResult(@Nonnull Map<String, Object> values) {
		this.values = values;
	}

	@Nullable
	@Override
	public Object getObject(@Nonnull String path) {
		return values.get(path);
	}

	@Nullable
	@Override
	public String getString(@Nonnull String path) {
		Object value = getObject(path);
		return value == null ? null : value.toString();
	}

	@Override
	public long getLong(@Nonnull String path, long def) {
		try {
			return Long.parseLong(getString(path));
		} catch (Exception ex) {
			return def;
		}
	}

	@Override
	public int getInt(@Nonnull String path, int def) {
		try {
			return Integer.parseInt(getString(path));
		} catch (Exception ex) {
			return def;
		}
	}

	@Override
	public short getShort(@Nonnull String path, short def) {
		try {
			return Short.parseShort(getString(path));
		} catch (Exception ex) {
			return def;
		}
	}

	@Override
	public byte getByte(@Nonnull String path, byte def) {
		try {
			return Byte.parseByte(getString(path));
		} catch (Exception ex) {
			return def;
		}
	}

	@Override
	public float getFloat(@Nonnull String path, float def) {
		try {
			return Float.parseFloat(getString(path));
		} catch (Exception ex) {
			return def;
		}
	}

	@Override
	public double getDouble(@Nonnull String path, double def) {
		try {
			return Double.parseDouble(getString(path));
		} catch (Exception ex) {
			return def;
		}
	}

	@Override
	public boolean getBoolean(@Nonnull String path, boolean def) {
		try {
			return Boolean.getBoolean(getString(path));
		} catch (Exception ex) {
			return def;
		}
	}

	@Nonnull
	@Override
	public List<String> getList(@Nonnull String path) {
		throw new UnsupportedOperationException("SQLResult.getList(String)");
	}

	@Nullable
	@Override
	public UUID getUUID(@Nonnull String path) {
		try {
			return UUID.fromString(getString(path));
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public boolean contains(@Nonnull String path) {
		return values.containsKey(path);
	}

	@Override
	public boolean isEmpty() {
		return values.isEmpty();
	}

	@Override
	public int size() {
		return values.size();
	}

	@Nonnull
	@Override
	public Document getDocument(@Nonnull String path) {
		try {
			return new ReadOnlyGsonDocument(getString(path));
		} catch (Exception ex) {
			return new EmptyDocument();
		}
	}

	@Nonnull
	@Override
	public Map<String, Object> values() {
		return Collections.unmodifiableMap(values);
	}

	@Nonnull
	@Override
	public Collection<String> keys() {
		return values.keySet();
	}

	@Override
	public void forEach(@Nonnull BiConsumer<? super String, ? super Object> action) {
		values.forEach(action);
	}

	@Nonnull
	@Override
	public String toJson() {
		return new GsonDocument(values).toJson();
	}

	@Override
	public String toString() {
		return toJson();
	}

}
