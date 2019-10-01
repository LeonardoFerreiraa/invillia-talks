package com.invillia.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        final List<String> shopList = new ArrayList<>();
        shopList.add("soap");
        shopList.add("bassoura");
        shopList.add("bassoura");
        shopList.add("beer");

        System.out.println(shopList);

    }

}
