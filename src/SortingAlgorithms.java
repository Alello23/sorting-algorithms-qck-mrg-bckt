package src;

import java.util.LinkedList;

public class SortingAlgorithms {




    public static void quickSortAge(LinkedList<Vessel> vessels) {
        quickSort(vessels, 0, vessels.size() - 1, true);
    }

    private static void quickSort(LinkedList<Vessel> vessels, int i, int j, boolean sortingAge) {
        int p;
        if (i >= j) {
            return;
        } else {
            p = partition(vessels, i, j, sortingAge);
            quickSort(vessels, i, p, sortingAge);
            quickSort(vessels, p + 1, j, sortingAge);

        }
    }

    private static int partition(LinkedList<Vessel> vessels, int i, int j, boolean sortingAge) {

        Vessel auxVessel;

        int l, r;
        int middle;
        int pivotInt = 0;
        double pivotDouble = 0;

        l = i;
        r = j;

        middle = (i + j) / 2;

        if (sortingAge) {
            pivotInt = vessels.get(middle).id;
        } else {
            pivotDouble = vessels.get(middle).getCapabilitiesRating();
        }

        while (true) {

            if (sortingAge) {
                while (vessels.get(l).id < pivotInt) {
                    l++;
                }
                while (vessels.get(r).id > pivotInt) {
                    r--;
                }
            } else {
                while (vessels.get(l).getCapabilitiesRating() < pivotDouble) {
                    l++;
                }
                while (vessels.get(r).getCapabilitiesRating() > pivotDouble) {
                    r--;
                }
            }

            if (l >= r) {
                return r;
            }

            auxVessel = vessels.get(l);
            vessels.set(l, vessels.get(r));
            vessels.set(r, auxVessel);

            l++;
            r--;

        }

    }

    //Function screen
    public static void mergeSortName(LinkedList<Vessel> vessels) {
        mergeSortName(vessels, 0, vessels.size() - 1);
    }


        /*This function split de linkedList until we have a linked-list with only one item */

    public static void mergeSortName(LinkedList<Vessel> vessels, int left, int right) {
        if (left < right) {
            //Find the middle of the linked-list.
            int middle = (left + right) / 2;

            //Call the function mergeSortName to split the two half
            mergeSortName(vessels, left, middle);
            mergeSortName(vessels, middle + 1, right);

            //mergesort the linked-list
            merge(vessels, left, middle, right);
        }
    }

    /* this function compare two letters and if is smaller they swap the element.*/

    private static void merge(LinkedList<Vessel> vessels, int left, int middle, int right) {

        int i, j, k;
        LinkedList<Vessel> B = null;


        B = (LinkedList<Vessel>) vessels.clone(); //clone the linked-list to the auxiliary one

        i = left;
        j = middle + 1;
        k = left;


        while (i <= middle && j <= right) //Swap the elements
            if (B.get(i).name.charAt(0) <= B.get(j).name.charAt(0))
                vessels.set(k++, B.get(i++));
            else
                vessels.set(k++, B.get(j++));

        while (i <= middle)                 //Copy the elements of the first half (if there is)
            vessels.set(k++, B.get(i++));
    }


        public static void bucketSortCapabilities (LinkedList <Vessel> vessels) {

            bucketSortCapabilities(vessels, vessels.size());
        }

        public static void bucketSortCapabilities (LinkedList <Vessel> vessels, int bucketsNumber) {
            int i;
            int j;

            int index;


            LinkedList<LinkedList<Vessel>> bucket = new LinkedList<>();

            for (i = 0; i < vessels.size(); i++) {
                bucket.add(new LinkedList<>());
            }

            for (i = 0; i < vessels.size(); i++) {
                index = (int) vessels.get(i).getCapabilitiesRating() * bucketsNumber;
                bucket.get(index).add(vessels.get(i));
            }

            for (i = 0; i < bucketsNumber; i++) {
                quickSort(bucket.get(i), 0, bucket.get(i).size() - 1, false);
                bucket.set(i, bucket.get(i));
            }
            index = 0;
            for (i = 0; i < bucketsNumber; i++) {
                for (j = 0; j < bucket.get(i).size(); j++) {
                    vessels.set(index, bucket.get(i).get(j));
                    index++;
                }
            }

        }
    }






