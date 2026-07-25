# 4. Git-HOL - Explanations

---

## 1. What is a Git Merge Conflict and How Does it Occur?

A **merge conflict** occurs when Git is unable to automatically reconcile differences between two commits during a merge operation.

### Scenarios Causing Merge Conflicts:
- **Concurrent Additions (add/add)**: Both branches create a file with the same relative path but different contents.
- **Concurrent Edits (modify/modify)**: Both branches modify the exact same line(s) in a file differently.
- **Delete/Modify Conflict**: One branch deletes a file while another branch modifies the same file.

---

## 2. Git Conflict Markers Explained

When a merge conflict occurs, Git marks up the conflicting file with conflict markers:

```xml
<greeting>
<<<<<<< HEAD
    <message>Hello World from Master Branch</message>
=======
    <message>Hello World from GitWork Branch</message>
>>>>>>> GitWork
</greeting>
```

- **`<<<<<<< HEAD`**: Marks the start of the changes from the target branch (`master`).
- **`=======`**: Separator dividing the target branch changes from the source branch changes.
- **`>>>>>>> GitWork`**: Marks the end of the changes from the merging branch (`GitWork`).

---

## 3. Conflict Resolution Workflow

1. **Identify Conflicts**: Run `git status` to locate files in `unmerged paths`.
2. **Open Conflict File**: Manually edit the file or launch a 3-way graphical merge tool like P4Merge (`git mergetool`).
3. **Choose / Combine Changes**: Remove all Git conflict markers (`<<<<<<<`, `=======`, `>>>>>>>`) and save the unified resolved content.
4. **Ignore Mergetool Backups**: Add `*.orig` to `.gitignore` to prevent committing P4Merge temporary backup files.
5. **Stage & Commit**: Stage resolved files (`git add hello.xml .gitignore`) and finalize the merge commit (`git commit`).
