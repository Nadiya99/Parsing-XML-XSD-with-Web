package by.epam.epamproject.entity;


/**
 * The type Treatment tour.
 */
public class TreatmentTour extends Tour {
    private String specialization;
    private String procedures;
    private String type;
    private static final String DEFAULT_SPECIALIZATION = "general";

    public TreatmentTour() {
        super();
    }

    public TreatmentTour(Tour tour) {
        super(tour);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (specialization != null && !specialization.isEmpty()) {
            this.specialization = specialization;
        } else {
            this.specialization = DEFAULT_SPECIALIZATION;
        }
    }

    public String getProcedures() {
        return procedures;
    }

    public void setProcedures(String procedures) {
        this.procedures = procedures;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TreatmentTour that = (TreatmentTour) o;

        if (specialization != null ? !specialization.equals(that.specialization) : that.specialization != null)
            return false;
        if (procedures != null ? !procedures.equals(that.procedures) : that.procedures != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
        result = 31 * result + (procedures != null ? procedures.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TreatmentTour{" +
                super.toString() +
                ", specialization='" + specialization + '\'' +
                ", procedures='" + procedures + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
