package org.osbot.updater.main;


import org.osbot.updater.analysers.MainAnalyser;
import org.osbot.updater.archive.ClassArchive;
import org.osbot.updater.utils.FileDownloader;

import java.io.File;


public class Loader {
    public static ClassArchive classArchive;
    private FileDownloader downloader;
    private MainAnalyser analyser;

    public Loader() {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                downloader = new FileDownloader("http://botupgrade.us/osbot/osbot.jar",
                        FileDownloader.getContentDirectory() + "osbot.jar");
                downloader.run();
                final File jar = new File(FileDownloader.getContentDirectory() + "osbot.jar");
                classArchive = new ClassArchive();
                classArchive.addJar(jar);

                analyser = new MainAnalyser();
                analyser.run();

            }

        });
        thread.start();

    }


}
