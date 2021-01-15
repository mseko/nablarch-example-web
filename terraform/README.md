# ���؊��\�z�菇

���̃f�B���N�g���ɂ́A���\���؂��s�����߂� AWS ���� Terraform �ō\�z���邽�߂̃X�N���v�g�Ȃǂ��z�u����Ă���B
�����ł́A���\�z�̎菇�ɂ��Đ�������B

## Terraform �ō\�z��������

�{�f�B���N�g������ Terraform �����s����ƁA�ȉ��̂��̂��\�z�ł���B

- ���\���؂̎��s��ՂƂȂ�AWS���\�[�X�̍\�z
- ���\���؂̏�����Ɓi�c�[���̃C���X�g�[����A�A�v���̃r���h�Ȃǁj

�܂�A Terraform �����s���邱�ƂŁu���Ƃ̓e�X�g�����s���邾���v�̏�Ԃ��\�z�ł���B

### ���\���؂̎��s��ՂƂȂ�AWS���\�[�X�̍\�z

AWS ���\�[�X�́A��܂��Ɉȉ��̂��̂��\�z�����B
�ڍׂ́A�e tf �t�@�C���̒��g���Q�Ƃ̂��ƁB

- `network.tf`
    - VPC �ƂR�̃T�u�l�b�g
        - �p�u���b�N�T�u�l�b�g�P�ƁA�v���C�x�[�g�T�u�l�b�g���Q��
    - EC2 �C���X�^���X�ȂǂɊ��蓖�Ă�Z�L�����e�B�O���[�v
- `ec2.tf`
    - �C���X�^���X�� SSH �ڑ�����Ƃ��Ɏg�p���� Key Pair
    - �R�� EC2 �C���X�^���X
        - ���ݑ�T�[�o�[(`performance-web-bastion-instance`)
        - ���\���؂̑ΏۂƂȂ�A�v���P�[�V�������ғ������� EC2 �C���X�^���X(`performance-web-ap-instance`)
        - JMeter�����s���� EC2 �C���X�^���X(`performance-web-jmeter-instance`)
- `rds.tf`
    - �A�v���P�[�V��������ڑ����� RDS �C���X�^���X�ƁA DB �T�u�l�b�g�O���[�v

### ���\���؂̏������

���\���؂̏�����Ƃł́A�傫���ȉ��̂��Ƃ��s����B

- JDK �ȂǁA�K�v�ȃ~�h���E�F�A�̃C���X�g�[��
- �A�v���P�[�V�����̃r���h�Ɣz��

�����̏����́A `aws_instance` �� `user_data` �Őݒ肷��V�F���X�N���v�g�Ŏ������Ă���B
�ڍׂ́A `ec2.tf` �̓��e���Q�Ƃ̂��ƁB

## �c�[���̃C���X�g�[��

�ȉ��̃c�[�����K�v�ɂȂ�̂ŁA���炩���߃C���X�g�[�����Ă������ƁB

- AWS CLI 2.0.14+
- Terraform v0.13.5+
- Git-Bash
    - Windows �̏ꍇ(SSH�L�[�̍쐬�Ɏg�p)

## �O�����

�ȉ��̏������ł��Ă��邱�Ƃ��O��ƂȂ�B

- AWS �����p�\�ȏ�ԂɂȂ��Ă��邱��
    - EC2, RDS ���\�z���錠��������IAM���[�U�̃A�N�Z�X�L�[�E�V�[�N���b�g�L�[���쐬�ł��Ă��邱��
- EIP ���쐬�ς݂ł��邱��
    - EC2 �C���X�^���X�Ɋ��蓖�Ă� EIP �͎��O�ɍ쐬���Ă���O��ł�

## SSH �L�[���쐬����

Git-Bash �Ŗ{�f�B���N�g���Ɉړ����A�ȉ��̃R�}���h�����s����B

```bash
$ mkdir ssh-key

$ ssh-keygen -t rsa -f ssh-key/performance -N ''
```

`ssh-key` �f�B���N�g���̉��� `performance`, `performance.pub` �̃t�@�C�����쐬�ł��Ă��邱�Ƃ��m�F����B

## AWS CLI �̔F�؏���ݒ肷��

�ȉ��̊��ϐ���ݒ肷��B

- `AWS_ACCESS_KEY_ID`
- `AWS_SECRET_ACCESS_KEY`
- `AWS_DEFAULT_REGION`

## Terraform �̕ϐ��t�@�C�����쐬����

���̃f�B���N�g���� `terraform.tfvars` �Ƃ����t�@�C�����쐬���A�ϐ����`����B
��`����ϐ��ɂ��ẮA `variables.tf` ���Q�Ɓi�f�t�H���g�l���������̂͒�`���K�{�j�B

## Terraform �����s����

�R�}���h���C���Ŗ{�f�B���N�g���Ɉړ����āA�ȉ��̃R�}���h�����s����B

```
$ terraform init

$ terraform apply
```

### EC2 �C���X�^���X�̏�����

EC2 �C���X�^���X���������Ɏ��s�����V�F���X�N���v�g�́A���s�����܂łɂ�����x���Ԃ�������B
���̂��߁A�N���シ���ɐڑ������i�K�ł́A�܂��X�N���v�g���������Ă��Ȃ��\��������B

�X�N���v�g���������Ă��邩�ǂ����́A `/var/log/messages` �� `tail` �ȂǂŊĎ����邱�ƂŊm�F�ł���B

## EIP �� EC2 �C���X�^���X(���ݑ�T�[�o�[)�̊֘A�t�����s��

Terraform �̎��s���������ē��ݑ�T�[�o�[�� EC2 �C���X�^���X���쐬���ꂽ��A EIP �Ƃ̊֘A�t�����s���B
����́A AWS �R���\�[���ォ��蓮�ōs���B

�Ȃ��A���ݑ�T�[�o�[�̖��O�� `performance-web-bastion-instance` �ō\�z����Ă���B

## SSH �Őڑ��ł��邩�m�F����

```bash
$ ssh -i ssh-key/performance ec2-user@<EIP��IP�A�h���X>
```

### AP�T�[�o�[�A JMeter �}�V���ɐڑ��ł��邩�m�F
```bash
# AP �T�[�o�[(performance-web-ap-instance)
$ ssh ec2-user@172.16.2.10

# JMeter �}�V��(performance-web-jmeter-instance)
$ ssh ec2-user@172.16.2.11
```

- SSH �̌��J���́A `performance-web-bastion-instance` �ɔz�������̂��̂Ɠ��������e�}�V���ɔz������Ă���
- �܂��A `performance-web-bastion-instance` �� `.ssh/id_rsa` �ɔ閧����z�����Ă���̂ŁA���ɏ�����Ƃ������� SSH �ڑ��̊m�F���ł���
- IP �A�h���X�́A `ec2.tf` �t�@�C����ύX���Ă��Ȃ���Ώ�L�A�h���X�Őڑ��ł���悤�ɍ\�z����Ă���

## �e�X�g�̎��s

`jmeter/README.md` ���Q�ƁB
