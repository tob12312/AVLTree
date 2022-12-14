package tree.avltree;

public class MergeSort {

    // SORTERINGSALGORITME MERGESORT ER AV ORDEN O(N LOG N) (WORSTE CASE)
    public static void mergeSort(int[] list) {
        if (list.length > 1) {

            // mergeSort første halvdel av lista
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf); // fortsetter å dele første halvdel i 2 og sortere

            //mergeSort andre halvdel av lista
            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            // merge de to halvdelene til felles liste
            merge(firstHalf, secondHalf, list);
        }
    }


    public static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] < list2[current2])
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }

        while (current1 < list1.length)
            temp[current3++] = list1[current1++];

        while (current2 < list2.length)
            temp[current3++] = list2[current2++];
    }
}
