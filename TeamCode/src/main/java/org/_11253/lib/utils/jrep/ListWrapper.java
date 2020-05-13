package org._11253.lib.utils.jrep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Custom implementation of Java's default List.
 * This is primarily used so items can be added as an array of
 * variable arguments instead of having to execute the add function
 * of the default list several times.
 * The list contained inside of this can still be retried, so
 * should, for some reason, you want to access that list, you
 * can do so with 'list name'.list.
 * TODO add a method of getting list values
 *
 * @param <E>
 */
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
