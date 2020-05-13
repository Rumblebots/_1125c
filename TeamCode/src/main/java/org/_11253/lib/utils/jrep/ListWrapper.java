package org._11253.lib.utils.jrep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListWrapper<E>
{
    public List<E> list;

    public ListWrapper ()
    {
        list = new ArrayList<>();
    }

    public ListWrapper (ArrayList<E> list)
    {
        this.list = list;
    }

    @SafeVarargs
    public final void add (E... es)
    {
        list.addAll(Arrays.asList(es));
    }
}
