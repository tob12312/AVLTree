package tree.avltree;

public class InsertionSort {

    // SORTERINGSALGORITME INSERTIONSORT ER AV ORDEN O(N2)
    public static void insertionSort(int[] list) {
        for (int i = 1; i < list.length; i++) { // begynner med andre element
            int currentElement = list[i]; // tmp var
            int k;
            // begynner på første element
            for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
                list[k + 1] = list[k]; // flytter elementer opp en indeks
            }

            list[k + 1] = currentElement;
        }
    }
}
