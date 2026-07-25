# 1. Git-HOL - Explanations

---

## 1. Git Initialization & Core Architecture

### Git Architecture Three States:
1. **Working Directory**: Local filesystem directory where files are created, edited, and stored before being tracked.
2. **Staging Area (Index)**: Intermediate workspace where modified files are staged using `git add` before committing.
3. **Local Repository (.git folder)**: The database where Git stores metadata, object store (`objects/`), refs (`refs/heads/master`), and commit history.

---

## 2. Fundamental Git Commands Explained

| Command | Description |
|---|---|
| `git --version` | Checks installed Git client version. |
| `git config --global user.name` | Sets global commit author name. |
| `git config --global user.email` | Sets global commit author email address. |
| `git config --global core.editor` | Configures external default text editor (e.g. Notepad++). |
| `git init` | Initializes a new empty Git repository and creates hidden `.git` folder. |
| `git status` | Displays working tree status, staged files, and untracked changes. |
| `git add <file>` | Stages specified file to the Index for the next commit. |
| `git commit -m "<msg>"` | Records staged snapshot into local repository history. |
| `git pull origin master` | Fetches and integrates changes from remote repository `master` branch. |
| `git push origin master` | Uploads local repository commits to remote repository `master` branch. |

---

## 3. Notepad++ Integration
- Configured via:
  ```bash
  git config --global core.editor "'C:/Program Files/Notepad++/notepad++.exe' -multiInst -notabbar -nosession -noPlugin"
  ```
- Command `git config --global -e` opens `.gitconfig` file directly inside Notepad++.
