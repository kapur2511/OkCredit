/*
 * Copyright (C) 02-Nov-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

/*
 * @author: kshitiz.kapur
 */

package com.okcredit.test.mvp.view.renderer;

import com.okcredit.test.mvp.view.viewmodel.ListItem;

import java.util.List;

public interface NewsListRenderer extends BaseRenderer{

    void renderNewsList(List<ListItem> newsViewModels);
}
