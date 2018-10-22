BankSys Git/GitHub Workflow

Step 1) Starting
$ git checkout master
$ git pull

Step 2) Creating a new work branch
$ git checkout –b <work_branch>

Step 3) Getting a updates from remote master
$ git checkout master
$ git pull

Step 4) Rebasing work branch
$ git checkout <work_branch>
$ git rebase master

Step 5) Merging
$ git checkout master
$ git merge <work_branch>

Step 6) Pushing
$ git push origin master