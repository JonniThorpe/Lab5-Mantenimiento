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
    public void next_withMoreElements_shouldReturnNextElement(){
        ArrayBoundedQueue array = new ArrayBoundedQueue<>(4);
        array.put(1);
        array.put(2);
        array.put(3);

        Iterator iterator = array.iterator();

        int n = (int)iterator.next();

        assertThat(n).isEqualTo(1);

    }

    @Test
    public void next_withoutMoreElements_shouldReturnException(){
        ArrayBoundedQueue array = new ArrayBoundedQueue<>(4);

        Iterator iterator = array.iterator();

        assertThatThrownBy(() -> iterator.next())
        .isInstanceOf(NoSuchElementException.class)
        .hasMessageContaining("next: bounded queue iterator exhausted");

    }

    @Test
    public void hasNext_withMoreElements_shouldReturnTrue(){
        ArrayBoundedQueue array = new ArrayBoundedQueue<>(4);
        array.put(1);
        array.put(2);
        array.put(3);

        Iterator iterator = array.iterator();

        boolean b = iterator.hasNext();
        assertThat(b).isEqualTo(true);
    }


    @Test
    public void hasNext_withoutMoreElements_shouldReturnFalse(){
        ArrayBoundedQueue array = new ArrayBoundedQueue<>(4);

        Iterator iterator = array.iterator();

        boolean b = iterator.hasNext();
        assertThat(b).isEqualTo(false);
    }
    public void testArrayBoundedQueue() {

        assertThat(ciudades).containsExactly("Malaga", "Sevilla", "Cordoba")
                .doesNotContain("Cadiz")
                .doesNotHaveDuplicates()
                .hasSize(3);

        ciudades.put("Cadiz");
        assertThat(ciudades).containsExactly("Malaga", "Sevilla", "Cordoba","Cadiz")
                .hasSize(4);

        //assertThat(edad).areAtLeastOne()


    }

    @Test
    public void testBoundedQueueIteration() {
        ciudades.get();
        assertThat(ciudades).containsExactly("Sevilla", "Cordoba");
        ciudades.put("Cadiz");
        ciudades.put("Malaga");
        assertThat(ciudades).containsExactly("Sevilla", "Cordoba","Cadiz","Malaga");
        assertThat(ciudades).last().isEqualTo("Malaga");
        assertThat(ciudades).first().isEqualTo("Malaga");
    }
}
