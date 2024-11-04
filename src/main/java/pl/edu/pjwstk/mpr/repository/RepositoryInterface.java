package pl.edu.pjwstk.mpr.repository;

import java.util.Map;

public interface RepositoryInterface<T> {
    public Map<Long, T> getDataBase();
}
