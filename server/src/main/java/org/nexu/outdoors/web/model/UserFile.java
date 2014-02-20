package org.nexu.outdoors.web.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserFile {

    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
