package net.ontrack.service;

import net.ontrack.core.model.Ack;
import net.ontrack.core.model.ExportData;

import java.util.Collection;

public interface ExportService {

    String exportLaunch(Collection<Integer> projectIds);

    Ack exportCheck(String uuid);

    ExportData exportDownload(String uuid);

    /**
     * Launches the import of
     *
     * @param importData Data to import
     * @return UUID to be used in
     */
    String importLaunch(ExportData importData);
}
