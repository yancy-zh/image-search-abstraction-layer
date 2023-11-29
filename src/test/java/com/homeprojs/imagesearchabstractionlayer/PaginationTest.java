package com.homeprojs.imagesearchabstractionlayer;

import com.homeprojs.imagesearchabstractionlayer.model.Image;
import com.homeprojs.imagesearchabstractionlayer.model.Pagination;
import com.homeprojs.imagesearchabstractionlayer.model.WriterReader;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PaginationTest {

    @Test
    void testGetPaginated1() {
        List<Integer> lsOfIntegers = new ArrayList<>();
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);

        Pagination<Integer> myPagination = new Pagination<>(lsOfIntegers, 3);
        assertEquals(Arrays.asList(1, 1, 1), myPagination.getPaginated(1));
    }

    @Test
    void testGetPaginated2() {
        List<Integer> lsOfIntegers = new ArrayList<>();
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);

        Pagination<Integer> myPagination = new Pagination<>(lsOfIntegers, 3);
        assertEquals(Arrays.asList(2, 2, 2), myPagination.getPaginated(2));
    }

    @Test
    void testGetPaginated3() {
        List<Integer> lsOfIntegers = new ArrayList<>();
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);

        Pagination<Integer> myPagination = new Pagination<>(lsOfIntegers, 3);
        assertEquals(Arrays.asList(3, 3, 3), myPagination.getPaginated(3));
    }

    @Test
    void testGetPaginated4() {
        List<Integer> lsOfIntegers = new ArrayList<>();
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);

        Pagination<Integer> myPagination = new Pagination<>(lsOfIntegers, 3);
        assertEquals(Arrays.asList(4, 4, 4), myPagination.getPaginated(4));
    }

    @Test
    void testGetPaginatedWithImageObj() {
        WriterReader writerReader = new WriterReader();
        List<Image> lsOfImgs = writerReader.readToObj("myObject.txt");
        List<Image> lsPage1 = Arrays.asList(lsOfImgs.get(0), lsOfImgs.get(1), lsOfImgs.get(2));
        Pagination<Image> myPagination = new Pagination<>(lsOfImgs, 3);
        assertThat(myPagination.getPaginated(1)).usingRecursiveComparison().isEqualTo(lsPage1);
    }
}
