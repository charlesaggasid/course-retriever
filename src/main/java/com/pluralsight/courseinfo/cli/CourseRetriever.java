package com.pluralsight.courseinfo.cli;

import com.pluralsight.courseinfo.cli.service.CourseRetrievalService;
import com.pluralsight.courseinfo.cli.service.PluralsightCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CourseRetriever {

    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String[] args) {
        LOG.info("Starting CourseRetriever...");

        if (args.length == 0) {
            LOG.warn("Please provide an author name as first argument.");
            return;
        }

        try {
        retrieveCourses(args[0]); //original one line code, add try catch
            //3.4.2
//            PluralsightCourse course =
//                    new PluralsightCourse("id","title", "00:54:57", "https://url", false);
//            LOG.info("course: {}", course);
        } catch (Exception e) {
            LOG.error("Unable to retrieve courses from database.", e);
            //e.printStackTrace(); //we can delete this since logger have the throwable e
        }

    }

    private static void retrieveCourses(String authorId) {
        LOG.info("Retrieving courses for '{}'", authorId);
        //Create a course retrieval service instance
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();

        //Retrieve courses for the specified author
        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCourserFor(authorId);
        LOG.info("Found {} courses", coursesToStore);

    }
}
