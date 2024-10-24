# Contributing to tmde app curricula
Thank you for your interest in contributing to tmde app curricula!
Patches/Pulls for features and bugfixes are welcome.
This document outlines the process for contributing, whether you’re reporting bugs, suggesting features, or submitting pull requests.

## How to Report Bugs
If you find a bug, please create an issue and provide as much detail as possible:

Describe the bug clearly, including steps to reproduce it.
Include screenshots or logs if possible.
Provide information about your environment, such as OS, browser version, or any other relevant configurations.
It is recommended to follow the bug report issue template.
You can report a bug by opening a new issue [here](https://github.com/fapret/tmde-app-curricula/issues/new?assignees=&labels=&projects=&template=bug_report.md&title=) or if its a vulnerability you can open an advisory [here](https://github.com/fapret/tmde-app-curricula/security/advisories/new)

## How to Suggest Enhancements
New ideas are always open! If you have suggestions on how to improve the project:

Describe the idea in detail and explain why it would be useful.
Include any potential drawbacks or challenges that might arise from the feature.
Discuss how the feature aligns with the project's goals.
You can suggest an enhancement by opening a new issue.

# How to Submit Changes
1. Fork the repository  
Start by forking the project repository and then cloning it to your local machine:  
`git clone https://github.com/fapret/tmde-app-curricula.git`

2. Use a feature-specific branch for your work  
Create a new branch for your changes:  
`git checkout -b feature/your-feature-name`  
Ensure you used branching guideline.

3. Make your changes

4. Commit your changes  
Write clear and concise commit messages.

5. Push to your fork and create a pull request  
After you've committed and pushed your changes, open a pull request from your branch to the `main` branch.

Before submitting your pull request, please ensure:
- Your code is well-documented.
- Avoid comments in other languages that are not english or spanish.
- Avoid unnecesary comments.
- All existing tests pass.
- If it's possible, you've added tests for new features or fixes.

## Branching guideline
For consistent branch naming, please follow the format below, this helps keep branches organized and makes the purpose of each branch clear:  
- `feature/` for enhancements.
- `bugfix/` for bugfixes.
- `docs/` for documentation.

(optionaly) You can add extra information adding also:  
- `front/` for front specific enhancements or bugfixes
- `back/` for back-end specific enhancements or bugfixes

Examples:  
- `feature/back/endpoint-of-issue-7`
- `bugfix/of-issue-X`
- `docs/of-issue-9`

# Code of Conduct
This project adheres to the [Code of Conduct](https://github.com/fapret/tmde-app-curricula/blob/main/CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code.

# License
By contributing to the project, you agree that your contributions will be licensed under the [LICENSE](https://github.com/fapret/tmde-app-curricula/blob/main/LICENSE) file in the root directory of this source tree.
