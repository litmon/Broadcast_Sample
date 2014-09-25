Broadcast_Sample
================
BroadcastReceiverのサンプルも兼ねて、前からずっとイライラしてたBluetoothのテザリング設定を自動化しようと思って
作りました。

## 概要
ServiceとBroadcastReceiverのみの単純な構成でできているので、起動しても画面もなにも動きません。
Bluetoothのデバイスを接続すると、自動的にBluetoothテザリングがONになるだけです。
それ以上でもそれ以下でもない本当にシンプルなアプリ(サービス？)

## 注意事項
デバイスのバージョンはHONEYCOMB以上を想定しているアプリです。
また、デバイスの画面が消えた状態でBluetoothの接続がONになっても、テザリングの方はONになりませぬ。
あと、完全に自己満足で作成したので、動作不良など訴えられても自己責任で。