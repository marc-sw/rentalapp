package de.talha.rentalapp.store;


import de.talha.rentalapp.abstraction.Decoder;
import de.talha.rentalapp.abstraction.Storable;
import de.talha.rentalapp.exception.EntityNotFound;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Store<T extends Storable> {
    private final FileAccess fileAccess;
    private final List<T> list;
    private final Decoder<T> decoder;

    public Store(FileAccess fileAccess, Decoder<T> decoder) {
        this.fileAccess = fileAccess;
        this.decoder = decoder;
        list = loadAll();
    }

    private int getNextId() {
        if (list.isEmpty()) {
            return 0;
        }
        return list.getLast().getId() + 1;
    }

    private List<T> loadAll() {
        List<T> data = new ArrayList<>();
        for (String line: fileAccess.readLines()) {
            if (line.isBlank()) {
                continue;
            }
            data.add(decoder.decode(line));
        }
        return data;
    }

    public T getById(int id) throws EntityNotFound {
        Optional<T> entity = list.stream().filter(e -> e.getId() == id).findFirst();
        if (entity.isEmpty()) {
            throw new EntityNotFound();
        }
        return entity.get();
    }

    public void create(T entity) {
        entity.setId(getNextId());
        list.add(entity);
        fileAccess.appendLine(entity.encode());
    }

    public void update(T updated) throws EntityNotFound {
        int index = 0;
        for (T entity: list) {
            if (entity.getId() == updated.getId()) {
                list.set(index, updated);
                fileAccess.editLine(index, updated.encode());
                return;
            }
            index++;
        }
        throw new EntityNotFound();
    }

    public void delete(int id) throws EntityNotFound {
        T entity = getById(id);
        int index = list.indexOf(entity);
        list.remove(entity);
        fileAccess.removeLine(index);
    }

    public List<T> getAll() {
        return new ArrayList<>(list);
    }

    public void deleteAll() {
        list.clear();
        fileAccess.clearFile();
    }
}
