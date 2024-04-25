package org.mps.boundedqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class ArrayBoundedQueueTest {
    @Test
    public void testArrayBoundedQueue() {}

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
}
