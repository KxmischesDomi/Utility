package net.anweisen.utilities.commons.config.document;

import net.anweisen.utilities.commons.config.Document;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class EmptyDocument implements Document {

	@Nonnull
	@Override
	public Document getDocument(@Nonnull String path) {
		return new EmptyDocument();
	}

	@Nonnull
	@Override
	public Document set(@Nonnull String path, @Nullable Object value) {
		throw new UnsupportedOperationException("EmptyDocument.set(String, Object)");
	}

	@Nonnull
	@Override
	public Document clear() {
		return this;
	}

	@Nonnull
	@Override
	public Document remove(@Nonnull String path) {
		return this;
	}

	@Override
	public void write(@Nonnull Writer writer) throws IOException {
		throw new UnsupportedOperationException("EmptyDocument.write(Writer)");
	}

	@Nullable
	@Override
	public Object getObject(@Nonnull String path) {
		return null;
	}

	@Nullable
	@Override
	public String getString(@Nonnull String path) {
		return null;
	}

	@Nonnull
	@Override
	public String getString(@Nonnull String path, @Nonnull String def) {
		return def;
	}

	@Override
	public char getChar(@Nonnull String path) {
		return 0;
	}

	@Override
	public char getChar(@Nonnull String path, char def) {
		return def;
	}

	@Override
	public long getLong(@Nonnull String path) {
		return 0;
	}

	@Override
	public long getLong(@Nonnull String path, long def) {
		return def;
	}

	@Override
	public int getInt(@Nonnull String path) {
		return 0;
	}

	@Override
	public int getInt(@Nonnull String path, int def) {
		return def;
	}

	@Override
	public short getShort(@Nonnull String path) {
		return 0;
	}

	@Override
	public short getShort(@Nonnull String path, short def) {
		return def;
	}

	@Override
	public byte getByte(@Nonnull String path) {
		return 0;
	}

	@Override
	public byte getByte(@Nonnull String path, byte def) {
		return def;
	}

	@Override
	public float getFloat(@Nonnull String path) {
		return 0;
	}

	@Override
	public float getFloat(@Nonnull String path, float def) {
		return def;
	}

	@Override
	public double getDouble(@Nonnull String path) {
		return 0;
	}

	@Override
	public double getDouble(@Nonnull String path, double def) {
		return def;
	}

	@Override
	public boolean getBoolean(@Nonnull String path) {
		return false;
	}

	@Override
	public boolean getBoolean(@Nonnull String path, boolean def) {
		return def;
	}

	@Nonnull
	@Override
	public List<String> getList(@Nonnull String path) {
		return Collections.emptyList();
	}

	@Nullable
	@Override
	public UUID getUUID(@Nonnull String path) {
		return null;
	}

	@Nonnull
	@Override
	public UUID getUUID(@Nonnull String path, @Nonnull UUID def) {
		return def;
	}

	@Nullable
	@Override
	public <E extends Enum<E>> E getEnum(@Nonnull String path, @Nonnull Class<E> classOfEnum) {
		return null;
	}

	@Nonnull
	@Override
	public <E extends Enum<E>> E getEnum(@Nonnull String path, @Nonnull E def) {
		return def;
	}

	@Override
	public boolean contains(@Nonnull String path) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public int size() {
		return 0;
	}

	@Nonnull
	@Override
	public Map<String, Object> values() {
		return Collections.emptyMap();
	}

	@Nonnull
	@Override
	public Collection<String> keys() {
		return Collections.emptyList();
	}

	@Override
	public void forEach(@Nonnull BiConsumer<? super String, ? super Object> action) {
	}

	@Nonnull
	@Override
	public String toJson() {
		return "{}";
	}

	@Override
	public boolean isReadonly() {
		return true;
	}

}
