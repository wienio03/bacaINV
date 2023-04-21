//Wienczyslaw Wlodyga

import java.util.Scanner;
public class Source {
    public static long Inversion(long[] array, int length){
        //tworzenie tablicy pomocniczej dlugosci maksymalnie (n/2)+1
        long[] tempArr = new long[(length / 2) + 1];
        int L = 0;
        int R = length - 1;
        return mergeSort(array, tempArr, L, R);
    }
    public static long mergeSort(long[] array, long[] auxArray, int leftIndex, int rightIndex){
        //zliczanie inwersji odbywa sie w rekurencyjnej metodzie mSort (merge sort)
        long inversions = 0;
        if(leftIndex < rightIndex) {
            int midPoint = (leftIndex + rightIndex) / 2;
            inversions += mergeSort(array, auxArray, leftIndex, midPoint);
            inversions += mergeSort(array, auxArray, midPoint, rightIndex);
            inversions += merge(array, auxArray, leftIndex, midPoint, rightIndex);
        }
        return inversions;
    }

    public static long merge(long[] array, long[] auxArray, int leftIndex, int mid, int rightIndex){
        int i = leftIndex, j = mid + 1, k = leftIndex;
        long inversions = 0;

        while(i <= mid && j <= rightIndex){
            if(array[i] <= array[j])
                auxArray[k++] = array[i++];
            else{
                inversions += (mid - i + 1);
                auxArray[k++] = array[j++];
            }
        }

        while(i <= mid)
            auxArray[k++] = array[i++];
        while(j <= rightIndex)
            auxArray[k++] = array[j++];

        int n = leftIndex;

        while(n <= rightIndex)
            array[n++] = auxArray[n++];

        return inversions;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sets;
        int arraySize;
        sets = scanner.nextInt();
        scanner.nextLine();
        for(int i = 0; i < sets; i++){
            arraySize = scanner.nextInt();
            scanner.nextLine();
            long[] array = new long[arraySize];
            for(int j = 0; j < arraySize; j++){
                array[j] = scanner.nextInt();
            }
            scanner.nextLine();
            System.out.println(Inversion(array, arraySize));
        }
    }
}