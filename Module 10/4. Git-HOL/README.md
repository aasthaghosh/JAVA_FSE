# 4. Git-HOL

Hands-on Lab covering Git merge conflict generation, conflict markup analysis, 3-way conflict resolution, `.gitignore` backup file handling, commit graph logging, and branch cleanup.

## Objectives Covered

1. **Conflict Simulation (`add/add`)**:
   - Created `GitWork` branch and committed [GitDemo/hello.xml](file:///c:/Users/NELAESH/OneDrive/Desktop/Cognizant/GIT/4.%20Git-HOL/GitDemo/hello.xml).
   - Switched to `master` and committed conflicting `hello.xml`.
   - Inspected commit graph before merge (`git log --oneline --graph --decorate --all`).
2. **Merge Conflict & Markup**:
   - Executed `git merge GitWork` to trigger `CONFLICT (add/add)`.
   - Analyzed conflict markers (`<<<<<<< HEAD`, `=======`, `>>>>>>> GitWork`).
3. **3-Way Conflict Resolution & Backup Ignorance**:
   - Manually/visually resolved `hello.xml`.
   - Added `*.orig` to [GitDemo/.gitignore](file:///c:/Users/NELAESH/OneDrive/Desktop/Cognizant/GIT/4.%20Git-HOL/GitDemo/.gitignore).
   - Staged and committed merge resolution (`git commit`).
4. **Branch Cleanup & Graph Log**:
   - Deleted merged branch `GitWork` (`git branch -d GitWork`).
   - Verified commit graph (`git log --oneline --graph --decorate`) and clean state (`git status`).

## Files

- [GitDemo/hello.xml](file:///c:/Users/NELAESH/OneDrive/Desktop/Cognizant/GIT/4.%20Git-HOL/GitDemo/hello.xml)
- [GitDemo/.gitignore](file:///c:/Users/NELAESH/OneDrive/Desktop/Cognizant/GIT/4.%20Git-HOL/GitDemo/.gitignore)
- [git_commands_log.txt](file:///c:/Users/NELAESH/OneDrive/Desktop/Cognizant/GIT/4.%20Git-HOL/git_commands_log.txt)
- [explanations.md](file:///c:/Users/NELAESH/OneDrive/Desktop/Cognizant/GIT/4.%20Git-HOL/explanations.md)
