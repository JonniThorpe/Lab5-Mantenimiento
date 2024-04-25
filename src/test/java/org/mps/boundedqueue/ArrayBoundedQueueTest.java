package org.mps.boundedqueue;

import org.junit.jupiter.api.BeforeEach;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ArrayBoundedQueueTest {
    private ArrayBoundedQueue<String> ciudades;
    private ArrayBoundedQueue<Integer> edad;

    @BeforeEach
    public void setUp() {
        ciudades = new ArrayBoundedQueue<>(4);
        ciudades.put("Malaga");
        ciudades.put("Sevilla");
        ciudades.put("Cordoba");

        edad = new ArrayBoundedQueue<>(3);
        edad.put(18);
        edad.put(30);
        edad.put(10);

    }

    @Test
    public void next_withMoreElements_shouldReturnNextElement() {
        ArrayBoundedQueue array = new ArrayBoundedQueue<>(4);
        array.put(1);
        array.put(2);
        array.put(3);

        Iterator iterator = array.iterator();

        int n = (int) iterator.next();

        assertThat(n).isEqualTo(1);

    }

    @Test
    public void next_withoutMoreElements_shouldReturnException() {
        ArrayBoundedQueue array = new ArrayBoundedQueue<>(4);

        Iterator iterator = array.iterator();

        assertThatThrownBy(() -> iterator.next())
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("next: bounded queue iterator exhausted");

    }

    @Test
    public void hasNext_withMoreElements_shouldReturnTrue() {
        ArrayBoundedQueue array = new ArrayBoundedQueue<>(4);
        array.put(1);
        array.put(2);
        array.put(3);

        Iterator iterator = array.iterator();

        boolean b = iterator.hasNext();
        assertThat(b).isEqualTo(true);
    }

    @Test
    public void hasNext_withoutMoreElements_shouldReturnFalse() {
        ArrayBoundedQueue array = new ArrayBoundedQueue<>(4);

        Iterator iterator = array.iterator();

        boolean b = iterator.hasNext();
        assertThat(b).isEqualTo(false);
    }

    @Test
    public void testArrayBoundedQueue() {

        assertThat(ciudades).containsExactly("Malaga", "Sevilla", "Cordoba")
                .doesNotContain("Cadiz")
                .doesNotHaveDuplicates()
                .hasSize(3);

        ciudades.put("Cadiz");
        assertThat(ciudades).containsExactly("Malaga", "Sevilla", "Cordoba", "Cadiz")
                .hasSize(4);

        // assertThat(edad).areAtLeastOne()

    }

    @Test
    public void testBoundedQueueIteration() {
        ciudades.get();
        assertThat(ciudades).containsExactly("Sevilla", "Cordoba");
        ciudades.put("Cadiz");
        ciudades.put("Malaga");
        assertThat(ciudades).containsExactly("Sevilla", "Cordoba", "Cadiz", "Malaga");
        assertThat(ciudades).last().isEqualTo("Malaga");
        assertThat(ciudades).first().isEqualTo("Sevilla");
    }

    @Test
    public void isFull_whenFull_shouldReturnTrue() {
        ciudades.put("Cadiz");
        boolean b = ciudades.isFull();
        assertThat(b).isTrue();
    }

    @Test
    public void isFull_whenNotFull_shouldReturnFalse() {
        boolean b = ciudades.isFull();
        assertThat(b).isFalse();
    }

    @Test
    public void getFirst_withoutUsingGet_ShouldReturnZero() {
        int n = ciudades.getFirst();
        assertThat(n).isEqualTo(0);
    }

    @Test
    public void getFirst_usingGet_ShouldReturnOne() {
        ciudades.get();
        int n = ciudades.getFirst();
        assertThat(n).isEqualTo(1);
    }

    @Test
    public void getLast_withoutUsingGet_ShouldReturnThree() {
        int n = ciudades.getLast();
        assertThat(n).isEqualTo(3);
    }

    @Test
    public void getLast_usingGet_ShouldReturnZero() {
        ciudades.put("Cadiz");
        ciudades.get();
        int n = ciudades.getLast();
        assertThat(n).isEqualTo(0);
    }

    @Test
    public void get_whenListIsEmpty_shouldReturnException() {
        ArrayBoundedQueue array = new ArrayBoundedQueue<>(4);

        assertThatThrownBy(() -> array.get())
                .isInstanceOf(EmptyBoundedQueueException.class)
                .hasMessageContaining("get: empty bounded queue");
    }

    @Test
    public void put_whenListIsFull_shouldReturnException() {
        ciudades.put("Cadiz");

        assertThatThrownBy(() -> ciudades.put("Madrid"))
                .isInstanceOf(FullBoundedQueueException.class)
                .hasMessageContaining("put: full bounded queue");
    }

    @Test
    public void put_whenElementIsNull_shouldReturnException() {

        assertThatThrownBy(() -> ciudades.put(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("put: element cannot be null");
    }

    @Test
    public void arrayBoundedQueue_withNegativeCapacity_shouldReturnException(){
        assertThatThrownBy(() -> new ArrayBoundedQueue(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("ArrayBoundedException: capacity must be positive");
    }

}
