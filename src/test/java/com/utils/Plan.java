package com.utils;

public class Plan {

    private String time;
    private String speed;
    private String cost;

    public Plan(String time, String speed, String cost) {
        this.time = time;
        this.speed = speed;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plan plan = (Plan) o;

        if (!cost.equals(plan.cost)) return false;
        if (!speed.equals(plan.speed)) return false;
        if (!time.equals(plan.time)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = time.hashCode();
        result = 31 * result + speed.hashCode();
        result = 31 * result + cost.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "time='" + time + '\'' +
                ", speed='" + speed + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
