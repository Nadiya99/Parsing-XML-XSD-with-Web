package by.epam.epamproject.parser;


import by.epam.epamproject.entity.Tour;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Abstract tours builder.
 */
public abstract class AbstractToursBuilder {

    /**
     * protected because they often cause subclass
     * The Tours.
     */
    protected Set<Tour> tours;

    /**
     * Instantiates a new Abstract tours builder.
     */
    public AbstractToursBuilder() {
        tours = new HashSet<Tour>();
    }

    /**
     * Instantiates a new Abstract tours builder.
     *
     * @param tours the tours
     */
    public AbstractToursBuilder(Set<Tour> tours) {
        this.tours = tours;
    }

    /**
     * Gets tours.
     *
     * @return the tours
     */
    public Set<Tour> getTours() {
        return tours;
    }

    /**
     * Build set tours.
     *
     * @param filename the file name
     */
    public abstract void buildSetTours(String filename);
}
