package by.epam.epamproject.entity;


public class HotelCharacteristic {
    private String mealPlan;
    private String starsCategory;
    private boolean tvIncluded;
    private boolean airConditioningIncluded;

    public String getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(String mealPlan) {
        this.mealPlan = mealPlan;
    }

    public String getStarsCategory() {
        return starsCategory;
    }

    public void setStarsCategory(String starsCategory) {
        this.starsCategory = starsCategory;
    }

    public boolean isTvIncluded() {
        return tvIncluded;
    }

    public void setTvIncluded(boolean tvIncluded) {
        this.tvIncluded = tvIncluded;
    }

    public boolean isAirConditioningIncluded() {
        return airConditioningIncluded;
    }

    public void setAirConditioningIncluded(boolean airConditioningIncluded) {
        this.airConditioningIncluded = airConditioningIncluded;
    }

    @Override
    public String toString() {
        return "{" +
                "mealPlan='" + mealPlan + '\'' +
                ", starsCategory='" + starsCategory + '\'' +
                ", tvIncluded=" + tvIncluded +
                ", airConditioningIncluded=" + airConditioningIncluded +
                '}';
    }
}