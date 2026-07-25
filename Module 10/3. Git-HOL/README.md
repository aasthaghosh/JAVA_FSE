# 3. Git-HOL

Hands-on Lab covering Git branching, switching, diffing, P4Merge visual tool setup, merging, graph log visualization, branch deletion, and GitLab Merge Request concepts.

## Objectives Covered

1. **Branching & Switching**:
   - `git branch GitNewBranch`
   - `git branch -a`
   - `git checkout GitNewBranch`
2. **Feature Development & Commits**:
   - Created [GitDemo/feature.txt](file:///c:/Users/NELAESH/OneDrive/Desktop/Cognizant/GIT/3.%20Git-HOL/GitDemo/feature.txt) on `GitNewBranch`.
   - Committed changes and verified with `git status`.
3. **Diffing, Merging & Graph Visualization**:
   - Switched to `master` (`git checkout master`).
   - `git diff master GitNewBranch`
   - Configured P4Merge difftool (`git config --global diff.tool p4merge`).
   - `git merge GitNewBranch`
   - `git log --oneline --graph --decorate`
4. **Cleanup & Deletion**:
   - `git branch -d GitNewBranch`
   - Confirmed branch removal and clean working tree.

## Files

- [GitDemo/main.txt](file:///c:/Users/NELAESH/OneDrive/Desktop/Cognizant/GIT/3.%20Git-HOL/GitDemo/main.txt)
- [GitDemo/feature.txt](file:///c:/Users/NELAESH/OneDrive/Desktop/Cognizant/GIT/3.%20Git-HOL/GitDemo/feature.txt)
- [git_commands_log.txt](file:///c:/Users/NELAESH/OneDrive/Desktop/Cognizant/GIT/3.%20Git-HOL/git_commands_log.txt)
- [explanations.md](file:///c:/Users/NELAESH/OneDrive/Desktop/Cognizant/GIT/3.%20Git-HOL/explanations.md)
