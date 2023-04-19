package gad.dynamicarray;

import java.util.Objects;

public abstract class Interval {

    public abstract int getFrom();

    public abstract int getTo();

    public abstract int getSize(int baseSize);

    public abstract boolean isEmpty();

    public static final class NonEmptyInterval extends Interval {
        private int from;
        private int to;

        @Override
        public int getFrom() {
            return from;
        }

        @Override
        public int getTo() {
            return to;
        }

        public NonEmptyInterval(int from, int to) {
            if (from < 0 || to < 0) {
                throw new RuntimeException("Negative interval boundaries are invalid!");
            }
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[" + from + "; " + to + "]";
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof NonEmptyInterval other) {
            	return from == other.from && to == other.to;
            } else {
            	return false;
            }
        }

        @Override
        /**
        * @param  baseSize  The size of the array
        * @return           The size of the interval in that array
        */
        public int getSize(int baseSize) {
            if (to >= baseSize || from >= baseSize) {
                throw new RuntimeException("Invalid interval for this base size!");
            }
            if (to >= from) {
                return to - from + 1;
            } else {
                return baseSize - (from - to - 1);
            }
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }

    public static final class EmptyInterval extends Interval {
        private static EmptyInterval singleton = new EmptyInterval();

        private EmptyInterval() {
        }

        public static EmptyInterval getEmptyInterval() {
            return singleton;
        }

        @Override
        public int getFrom() {
            throw new RuntimeException("No lower boundary in empty interval");
        }

        @Override
        public int getTo() {
            throw new RuntimeException("No upper boundary in empty interval");
        }

        @Override
        public String toString() {
            return "[]";
        }

        @Override
        public int getSize(int baseSize) {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }
}
