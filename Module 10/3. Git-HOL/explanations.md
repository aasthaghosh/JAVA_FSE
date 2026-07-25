# 3. Git-HOL - Explanations

---

## 1. Branching and Merging Concepts

### What is a Git Branch?
- A **branch** in Git is simply a lightweight, movable pointer to a specific commit.
- The default primary branch is named `master` (or `main`).
- Creating branches allows developers to isolate feature development, bug fixes, or experimental work without affecting production code.

### Merge Types:
1. **Fast-Forward Merge**: Occurs when the target branch (`master`) has no new commits since the feature branch was created. Git moves the pointer directly forward to the feature branch's tip commit.
2. **3-Way (Recursive) Merge**: Occurs when both `master` and the feature branch have diverged with independent commits. Git uses a common ancestor commit to create a new **Merge Commit**.

---

## 2. GitLab Merge Requests / GitHub Pull Requests

### What is a Merge Request (MR) / Pull Request (PR)?
- A **Merge Request** (in GitLab) or **Pull Request** (in GitHub) is a mechanism for a developer to request that their feature branch changes be reviewed and merged into a target branch (e.g. `master` / `main`).

### Key MR/PR Steps:
1. **Create Branch & Push**: Developer pushes feature branch `feature/user-auth` to remote.
2. **Open Merge Request**: Developer opens MR specifying source branch (`feature/user-auth`) and target branch (`master`).
3. **Code Review & Automated CI/CD**: Peer reviewers inspect code diffs, run unit tests, and approve changes.
4. **Merge & Delete**: Once approved, MR is merged into `master` and the feature branch is deleted.

---

## 3. P4Merge Integration
- **P4Merge** by Perforce is a graphical 3-way visual diff and merge tool.
- Configured in Git via:
  ```bash
  git config --global diff.tool p4merge
  git config --global difftool.p4merge.cmd '"C:/Program Files/Perforce/p4merge.exe" "$LOCAL" "$REMOTE"'
  ```
- Command `git difftool branch1 branch2` launches P4Merge interface to compare visual side-by-side file diffs.
