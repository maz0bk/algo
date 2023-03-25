import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ArrayImplTestCase {

    public static final int INVALID_VALUE = 777;

    @Test
    public void test_init_array(){
        Array<Integer> array= new ArrayImpl<>();
        array.add(1);
        array.add(2);
        Assert.assertThat(array.getSize(), Is.is(2));
        Assert.assertThat(array.get(0), Is.is(1));
        Assert.assertThat(array.get(1), Is.is(2));
    }

    @Test
    public void test_init_array_fixed_size(){
        Array<Integer> array= new ArrayImpl<>(2);
        array.add(1);
        array.add(2);
        array.add(3);
        Assert.assertThat(array.getSize(), Is.is(3));
        Assert.assertThat(array.get(0), Is.is(1));
        Assert.assertThat(array.get(1), Is.is(2));
        Assert.assertThat(array.get(2), Is.is(3));
    }

    @Test
    public void test_search(){
        Array<Integer> array = new ArrayImpl<>();
        array.add(2);
        array.add(3);
        array.add(5);

        Assert.assertTrue(array.contains(2));
        Assert.assertTrue(array.contains(3));
        Assert.assertTrue(array.contains(5));
        Assert.assertFalse(array.contains(INVALID_VALUE));

        Assert.assertThat(array.indexOf(2),Is.is(0));
        Assert.assertThat(array.indexOf(3),Is.is(1));
        Assert.assertThat(array.indexOf(5),Is.is(2));
        Assert.assertThat(array.indexOf(INVALID_VALUE),Is.is(-1));

    }

    @Test
    public void test_remove_by_index(){
        Array<Integer> array = new ArrayImpl<>();
        array.add(2);
        array.add(3);
        array.add(5);

        array.remove(0);
        Assert.assertThat(array.getSize(),Is.is(2));
        Assert.assertThat(array.indexOf(3),Is.is(0));
        Assert.assertThat(array.indexOf(5),Is.is(1));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void test_remove_by_invalid_index(){
        Array<Integer> array = new ArrayImpl<>();
        array.add(2);
        array.add(3);
        array.add(5);

        array.remove(3);

    }

    @Test
    public void test_remove_by_value(){
        Array<Integer> array = new ArrayImpl<>();
        array.add(2);
        array.add(3);
        array.add(5);

        Assert.assertFalse(array.remove(Integer.valueOf(INVALID_VALUE)));
        Assert.assertTrue(array.remove(Integer.valueOf(2)));
        Assert.assertThat(array.getSize(),Is.is(2));
        Assert.assertThat(array.indexOf(3),Is.is(0));
        Assert.assertThat(array.indexOf(5),Is.is(1));
    }
    @Test
    public void test_sorted_array(){
        Array<Integer> array = new SortedArrayImpl<>();
        array.add(9);
        array.add(3);
        array.add(5);

        Assert.assertThat(array.getSize(), Is.is(3));
        Assert.assertThat(array.get(0), Is.is(3));
        Assert.assertThat(array.get(1), Is.is(5));
        Assert.assertThat(array.get(2), Is.is(9));
    }
    @Test
    public void test_binary_search(){
        Array<Integer> array = new SortedArrayImpl<>();
        array.add(9);
        array.add(3);
        array.add(5);

        Assert.assertTrue(array.contains(9));
        Assert.assertTrue(array.contains(3));
        Assert.assertTrue(array.contains(5));
        Assert.assertFalse(array.contains(INVALID_VALUE));

        Assert.assertThat(array.indexOf(9),Is.is(2));
        Assert.assertThat(array.indexOf(3),Is.is(0));
        Assert.assertThat(array.indexOf(5),Is.is(1));
        Assert.assertThat(array.indexOf(INVALID_VALUE),Is.is(-1));

    }
    @Test
    public void test_bubble_sort(){
        Array<Integer> array = new ArrayImpl<>();
        array.add(9);
        array.add(3);
        array.add(5);
        array.add(8);
        array.add(2);

        array.sortBubble();
        Assert.assertThat(array.get(0),Is.is(2));
        Assert.assertThat(array.get(1),Is.is(3));
        Assert.assertThat(array.get(2),Is.is(5));
        Assert.assertThat(array.get(3),Is.is(8));
        Assert.assertThat(array.get(4),Is.is(9));
    }

    @Test
    public void test_select_sort(){
        Array<Integer> array = new ArrayImpl<>();
        array.add(9);
        array.add(3);
        array.add(5);
        array.add(8);
        array.add(2);

        array.sortSelect();
        Assert.assertThat(array.get(0),Is.is(2));
        Assert.assertThat(array.get(1),Is.is(3));
        Assert.assertThat(array.get(2),Is.is(5));
        Assert.assertThat(array.get(3),Is.is(8));
        Assert.assertThat(array.get(4),Is.is(9));
    }

    @Test
    public void test_insert_sort(){
        Array<Integer> array = new ArrayImpl<>();
        array.add(9);
        array.add(3);
        array.add(5);
        array.add(8);
        array.add(2);

        array.sortInsert();
        Assert.assertThat(array.get(0),Is.is(2));
        Assert.assertThat(array.get(1),Is.is(3));
        Assert.assertThat(array.get(2),Is.is(5));
        Assert.assertThat(array.get(3),Is.is(8));
        Assert.assertThat(array.get(4),Is.is(9));
    }

    @Test
    public void test_speed() throws InterruptedException {
        Array <Integer> array = new ArrayImpl<>();
        Array <Integer> array1 = new ArrayImpl<>();
        Array <Integer> array2 = new ArrayImpl<>();
        Random random = new Random();
        int upperbound = 500000;
        int tmpValue;
        for (int i = 0; i < 10000; i++) {
            tmpValue = random.nextInt(upperbound);
            array.add(tmpValue);
            array1.add(tmpValue);
            array2.add(tmpValue);
        }

        new Thread(()->{
            System.out.println("Start Bubble");
            Long timeStart = System.nanoTime();
            array.sortBubble();
            Long timeFinish = System.nanoTime();
            System.out.println("Bubble Start:"+timeStart+" Finish:"+timeFinish+" Total:"+ TimeUnit.NANOSECONDS.toMillis(timeFinish-timeStart));
        }){{start();}}.join();

        new Thread(()->{
            System.out.println("Start Insert");
            Long timeStart = System.nanoTime();
            array1.sortInsert();
            Long timeFinish = System.nanoTime();
            System.out.println("Insert Start:"+timeStart+" Finish:"+timeFinish+" Total:"+ TimeUnit.NANOSECONDS.toMillis(timeFinish-timeStart));
        }){{start();}}.join();

        new Thread(()->{
            System.out.println("Start Select");
            Long timeStart = System.nanoTime();
            array2.sortSelect();
            Long timeFinish = System.nanoTime();
            System.out.println("Select Start:"+timeStart+" Finish:"+timeFinish+" Total:"+ TimeUnit.NANOSECONDS.toMillis(timeFinish-timeStart));
        }){{start();}}.join();



    }
}
