package com.pluralsight.courseinfo.cli;

public class CourseRetriever {
    public static void main(String[] args) {
        System.out.println("CourseRetriever started!");
        if (args.length == 0) {
            System.out.println("Please provide an author name as first argument.");
            return;
        }

        try {
        retrieveCourses(args[0]); //original one line code, add try catch
        } catch (Exception e) {
            System.out.println("Unable to retrieve courses from database.");
            e.printStackTrace();
        }

    }

    private static void retrieveCourses(String authorId) {
        System.out.println("Retrieving courses for " + authorId);
    }
}
