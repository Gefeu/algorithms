因为对格子进行dfs，无法朝着多个方向，只能对格子边线进行dfs。

dfs适合搜索不同的路径，不适合搜索不同的区域（会有大量重复，效率很低），
但可以通过遍历判断区域连通性

dfs有回溯和不回溯两种版本，该问题用的是回溯的版本。