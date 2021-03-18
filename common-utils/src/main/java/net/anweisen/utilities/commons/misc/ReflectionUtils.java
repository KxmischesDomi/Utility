package net.anweisen.utilities.commons.misc;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 2.0
 */
public final class ReflectionUtils {

	private ReflectionUtils() {}

	@Nonnull
	public static Collection<Method> getDeclaredMethodsAnnotatedWith(@Nonnull Class<?> clazz, @Nonnull Class<? extends Annotation> classOfAnnotation) {
		return filterMethodsAnnotatedWith(clazz.getDeclaredMethods(), classOfAnnotation);
	}

	@Nonnull
	public static Collection<Method> getMethodsAnnotatedWith(@Nonnull Class<?> clazz, @Nonnull Class<? extends Annotation> classOfAnnotation) {
		return filterMethodsAnnotatedWith(clazz.getMethods(), classOfAnnotation);
	}

	@Nonnull
	private static Collection<Method> filterMethodsAnnotatedWith(@Nonnull Method[] methods, @Nonnull Class<? extends Annotation> classOfAnnotation) {
		List<Method> annotatedMethods = new ArrayList<>();
		for (Method method : methods) {
			if (!method.isAnnotationPresent(classOfAnnotation)) continue;
			annotatedMethods.add(method);
		}
		return annotatedMethods;
	}

	/**
	 * @param classOfEnum The class containing the enum constants
	 * @return The first enum found by the given names
	 */
	@Nonnull
	public static <E extends Enum<E>> E getEnumByNames(@Nonnull Class<E> classOfEnum, @Nonnull String... names) {
		for (String name : names) {
			try {
				return Enum.valueOf(classOfEnum, name);
			} catch (IllegalArgumentException | NoSuchFieldError ex) { }
		}
		throw new IllegalArgumentException("No enum found in " + classOfEnum.getName() + " for " + Arrays.toString(names));
	}

	/**
	 * Iterates through an array which may contain primitive data types or non primitive data types and performs the given action on each element.
	 * Because we can't just cast such an array to {@code Object[]}, we have to use some reflections.
	 *
	 * @param array The target array, as {@link Object}; Can't use an array type here.
	 * @param <T> The type of data we will cast the content to. Use {@link Object} if the it's unknown.
	 *
	 * @throws IllegalArgumentException
	 *         If the {@code array} is not an actual array
	 *
	 * @see Array
	 * @see Array#getLength(Object)
	 * @see Array#get(Object, int)
	 */
	public static <T> void forEachInArray(@Nonnull Object array, @Nonnull Consumer<T> action) {
		int length = Array.getLength(array);
		for (int i = 0; i < length; i++) {
			Object object = Array.get(array, i);
			action.accept((T) object);
		}
	}

	@CheckReturnValue
	public static Class<?> getCaller(int index) {
		try {
			return new PublicSecurityManager().getPublicClassContext()[index + 2];
		} catch (Exception ignored) {
			return null;
		}
	}

	@CheckReturnValue
	public static Class<?> getCaller() {
		try {
			return getCaller(2);
		} catch (Exception ignored) {
			return null;
		}
	}

}
