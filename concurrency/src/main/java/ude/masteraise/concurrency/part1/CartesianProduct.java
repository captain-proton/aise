package ude.masteraise.concurrency.part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by nils on 29.04.16.
 */
public class CartesianProduct {

    List<Object[]> product;

    private List<Object[]> build(Object[]...arrs) {
        product = new ArrayList<>();
        build(Arrays.asList(arrs), new Object[arrs.length], 0);
        return product;
    }

    private void build(List<Object[]> arrs, Object[] curr, int k) {
        int size = arrs.size();
        if (k == size) {
            Object[] res = new Object[size];
            System.arraycopy(curr, 0, res, 0, size);
            product.add(res);
        } else {
            Object[] arr = arrs.get(k);
            for (int j = 0; j < arr.length; j++) {
                curr[k] = arr[j];
                build(arrs, curr, k + 1);
            }
        }
    }


    public static void main(String[] args) {

        Object[] z1 = {"idle", "want", "crit"};
        Object[] z2 = {"idle", "want", "crit"};
        Object[] a1 = {"a", "b"};
        Object[] a2 = {"a", "b"};

        CartesianProduct product = new CartesianProduct();
        List<Object[]> build = product.build(z1, z2, a1, a2);
        for (Object[] o : build) {
            System.out.print("[ ");
            List<String> strings = Stream.of(o).map(obj -> obj.toString()).collect(Collectors.toList());
            System.out.print(String.join(", ", strings));
            System.out.println(" ]");
        }
    }

}
