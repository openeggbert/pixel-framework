///////////////////////////////////////////////////////////////////////////////////////////////
// Pixel: LibGDX Backend.
// Copyright (C) 2024 the original author or authors.
//
// This program is free software: you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation, either version 3
// of the License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see
// <https://www.gnu.org/licenses/> or write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////
package com.pixelgamelibrary.api.utils.collections;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author robertvokac
 * @param <T> item
 */
public abstract class PixelCollection<T> implements Collection<T> {

    @Override
    public final Object[] toArray() {
        Iterator<T> iterator = iterator();
        Object[] array = new Object[size()];
        int i = 0;
        while (iterator.hasNext()) {
            array[i] = iterator.next();
            i++;
        }
        return array;
    }

    @Override
    public final <T> T[] toArray(T[] a) {
        Iterator<T> iterator = (Iterator<T>) iterator();
        Object[] array = a;
        int i = 0;
        while (iterator.hasNext()) {
            array[i] = iterator.next();
            i++;
        }
        return (T[]) array;
    }

    @Override
    public final boolean containsAll(Collection<?> c) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            boolean contains = contains(iterator.next());
            if (!contains) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final boolean removeAll(Collection<?> c) {
        boolean allKeysRemoved = true;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            boolean removed = remove(iterator.next());
            if (!removed && allKeysRemoved) {
                allKeysRemoved = false;
            }
        }
        return allKeysRemoved;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
